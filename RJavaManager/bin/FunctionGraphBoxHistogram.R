# --------------------------------------------------------
# ARGUMENTS:
# data - a dataframe
# respvar - a string; variable name of the response variable
# env - a string; variable name of the environment variable
# result - output of single environment analysis
# box - logical; indicates if boxplot(s) is(are) to be created
# hist - logical; indicates if histogram(s) is(are) to be created
# --------------------------------------------------------


#FunctionGraphBoxHistogram <- function(dataName, respvar, env = NULL, result, box = FALSE, hist = FALSE) {
FunctionGraphBoxHistogram <- function(dataName, respvar, env = NULL, box = FALSE, hist = FALSE) {
	
  dir.create("plots")
  
  #create R data set given csv file named dataName
  data <- read.csv(dataName, header = TRUE, na.strings = c("NA","."), blank.lines.skip = TRUE, sep = ",")
  
  if (is.null(env)) {
		withEnv = FALSE
		data <- cbind(data, ENV="1")
		names(data) <- make.unique(c(names(data)))
		env = names(data)[length(names(data))]
	} else {
		withEnv = TRUE
	}
  
  if (box == TRUE) {
    #create boxplot of raw data (1 file for each respvar)
    #for (i in (1:length(respvar))) {
  		boxfile = paste(getwd(),"/plots/boxplot_",respvar,".png",sep = "")
  		png(file = boxfile) #par(mfrow = n2mfrow(length(respvar)));
  		xlabel = respvar
  		boxplot(data[,match(respvar, names(data))]~data[,match(env, names(data))],data = data,xlab = xlabel);
  		dev.off()
  	#}
  }
	
  if (hist == TRUE) {
    #create histogram of the raw data (1 file for each envt in each respvar)
  	#for (i in (1:length(respvar))) {
  		for (j in (1:nlevels(data[,match(env, names(data))]))) {
  		  siteLabel = result$output[[i]]$site[[j]]$env[[1]]
        if (withEnv) {histfile = paste(getwd(),"/plots/hist_",respvar[i],"_",siteLabel,".png",sep="")}
  		  else {histfile = paste(getwd(),"/plots/hist_",respvar[i],".png",sep="")}
        png(file = histfile) # par(mfrow = n2mfrow(length(respvar)*nlevels(data[,match(env, names(data))])));
				xlabel = respvar[i]
   			if (withEnv) {hist(result$output[[i]]$site[[j]]$data[,match(respvar[i], names(result$output[[i]]$site[[j]]$data))], xlab = xlabel,main = paste("Site: ", siteLabel))}
  		  else {hist(result$output[[i]]$site[[j]]$data[,match(respvar[i], names(result$output[[i]]$site[[j]]$data))], xlab = xlabel, main = "Histogram")}
  			dev.off()
  		}	
  	#}	
  }	
  
}
