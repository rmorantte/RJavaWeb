allPossibleReg <- function(data, yVars, xVars, selMeasure = "mallow", constant = TRUE) {
  library(leaps)  
  #reads data
  if (is.character(data)) { data <- eval(parse(text = data)) }
  
  for (m in 1:length(yVars)) {
    #missing values?
    data <- na.omit(data)
    
    ncombn <- vector()
#     if (constant) { 
    nXVars = length(xVars)
#     } else nXVars = length(xVars) - 1
    for (k in 1:nXVars) {
      ncombn[[k]] <- dim(combn(nXVars,k))[2]
    }
    bigN <- max(ncombn)
    regModel <- paste(yVars[m] , "~", paste(xVars, collapse = " + ")) 
    regCommand <- paste("reg1 <- regsubsets(", regModel, ", data = data, nbest = ", bigN, ", intercept = ", constant, ", really.big = TRUE)", sep = "")
    eval(parse(text = regCommand))
    sumReg1<-summary(reg1)
    selStat <- function(meas) {
      switch(meas, 
           mallow = sumReg1$cp,
           rsquare = sumReg1$rsq,
           adjrsq = sumReg1$adjr2)
    }
    selStatName <- function(meas) {
      switch(meas, 
             mallow = "Mallow's C(p)",
             rsquare = "R-Square",
             adjrsq = "Adjusted R-Square")
    }
    selStats <- selStat(selMeasure)
    startInd = 1
    desStats <- vector()
    desInd <- vector()
    selXVars <- vector()
    regResult <- NULL
    for (o in 1:nXVars){  #length(xVars)
      batchEnd <- startInd+ncombn[o]-1
      #determine best "model" based on statistic
      if (selMeasure == "mallow") {
        desStats[[o]] = min(abs(selStats[startInd:batchEnd] - o))
        desInd[[o]] <- which(abs(selStats[startInd:batchEnd] - o) == desStats[[o]]) + startInd - 1
      } else { 
        desStats[[o]] <- max(selStats[startInd:batchEnd])
        desInd[[o]] <- which(selStats[startInd:batchEnd] == desStats[[o]]) + startInd - 1} #data[which(data[,byVar] == levels(data[,byVar])[m]),]
      startInd <- startInd + ncombn[o]
      if (constant) { selVarsInd <- which(sumReg1$which[desInd[[o]],-1])
      } else selVarsInd <- which(sumReg1$which[desInd[[o]],])
      
      selXVars[[o]] <- paste(names(selVarsInd),collapse=" + ") #       cbind(paste(selXVars,collapse=" "),selCp,selRSq,selAdjRSq)
      dataO <- data[,c(yVars[m], names(selVarsInd))] # dataO <- data[,c(yVars[m],xVars[selVarsInd])]
#       regCommand <- paste("regResult[[", o, "]] <- lm(", yVars[m], " ~., data = dataO)", sep = "")
#       if (constant) { regCommand <- paste("regResult[[", o, "]] <- lm(", yVars[m], " ~ ", paste(xVars[selVarsInd], collapse = "+"),", data = dataO)", sep = "")
#       } else { regCommand <- paste("regResult[[", o, "]] <- lm(", yVars[m], " ~ ", paste(xVars[selVarsInd+1], collapse = "+"), "-1, data = dataO)", sep = "")}
      if (constant) { regCommand <- paste("regResult[[", o, "]] <- lm(", yVars[m], " ~ ", selXVars[[o]],", data = dataO)", sep = "")
      } else { regCommand <- paste("regResult[[", o, "]] <- lm(", yVars[m], " ~ ", selXVars[[o]], "-1, data = dataO)", sep = "")}
      
      eval(parse(text = regCommand))
    }
    selCp <- format(sumReg1$cp[desInd],digits=4, justify = "centre")
    selRSq <- format(sumReg1$rsq[desInd]*100,digits=4, justify = "centre") #in pct
    selAdjRSq <- format(sumReg1$adjr2[desInd]*100,digits=4, justify = "centre") #in pct
    selTable <- as.data.frame(cbind(format(selXVars,justify = "left"), selCp, selRSq, selAdjRSq))
    
    colnames(selTable) = c(format("Variables", width = nchar(max(selXVars)), justify = "centre"), 
                           format("C(p)", width = nchar(max(selCp)), justify = "centre"),
                           format("R-square", width = nchar(max(selRSq)), justify = "centre"),
                           format("Adj R-sq", width = nchar(max(selRSq)), justify = "centre"))
    
#     cat("\nMultiple Linear Regression\n")
    cat("\nResponse Variable: ", yVars[m], "\n")
    cat("\nAll-possible regression (", selStatName(selMeasure), ")\n", sep = "")
    cat("\nSummary Table: \n")
    printDataFrame(selTable)
    cat("\n")
    
    for (o in 1:length(xVars)) {
#      cat("\nModel ", o, ": ",yVars[m], " ~ ", paste(selXVars[o], collapse = " + "), sep="")
     cat("Model ", o, ": ", sep="")
     cat("\nIndependent Variables: ", selXVars[o])
     cat("\n\nParameter Estimates:\n", sep="")
#      print(signif(summary(regResult[[o]])$coefficients), digits = 4)
#      if (constant) { rownames(tempResult$coefficient)[1] <- "Intercept" }
     
     coefTable <- data.frame(rownames(summary(regResult[[o]])$coefficient),summary(regResult[[o]])$coefficient)
     colnames(coefTable) <- c("Variable", attributes(summary(regResult[[o]])$coefficient)$dimnames[[2]])
     coefTable[,1] <- as.character(coefTable[,1])
     if (constant) { coefTable[1,1] <- "Intercept" }
     printDataFrame(coefTable)
     cat("\n")
     
    }    
  }
}