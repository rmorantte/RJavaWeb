# --------------------------------------------------------
# CreateGraphHistogram
# function for constructina a histogram
# ARGUMENTS:
# wdPath - working directory path
# dataName - name of .csv file that will be created as an R data
# respvar - a string; variable name of the response variable
# --------------------------------------------------------


CreateGraphHistogram <- function(wdPath, dataName, respvar) {
	
  	#setwd("D:\\PBTools_workspace\\RJavaManager\\sample_datasets")
	setwd(wdPath)
  	dir.create("plots")
  
	#create R data set given csv file named dataName
  	data <- read.csv(dataName, header = TRUE, na.strings = c("NA","."), blank.lines.skip = TRUE, sep = ",")
  
	histfile = paste(getwd(),"/plots/hist_",respvar,".png",sep="")
    png(file = histfile) # par(mfrow = n2mfrow(length(respvar)*nlevels(data[,match(env, names(data))])));
  	xlabel = respvar
	hist(data[,match(respvar, names(data))], xlab = xlabel, main = "Histogram")
 	dev.off()
 
}
