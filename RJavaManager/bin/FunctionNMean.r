FunctionNMean <- function(data1, var1) {

  temp.data = data1[[match(var1, names(data1))]]
  summaryTable <- NULL

  a <- NULL
  a <- rbind(a, as.data.frame.table(length(temp.data)))
  summaryTable <- a
  colnames(summaryTable)[ncol(summaryTable)] <- "N_Obs"

  a <- NULL
  a <- rbind(a, as.data.frame.table(mean(temp.data, na.rm = TRUE)))
  ifelse(is.null(summaryTable), summaryTable <- a, summaryTable <- cbind(summaryTable, a[ncol(a)]))
  colnames(summaryTable)[ncol(summaryTable)] <- "Mean"
  
  return(summaryTable)
}

#setwd("C:\\Documents and Settings\\rmorantte\\Desktop\\pbtools")
#data1 <- read.csv("data1.csv")
#var1<- "Yield"
#d <- fncn.desc(data1,var1)
#d