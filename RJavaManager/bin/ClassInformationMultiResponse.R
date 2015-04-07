ClassInformationMultiResponse <- function(data, respvar = NULL) {
	result <- DataAttribute(data)
	result <- result[result[,"TYPE"] == "factor",c(1,3,4)]
	result[,2] <- as.numeric(result[,2])
	colnames(result) <- c("FACTOR", "NO. OF LEVELS", "LEVELS")
	#cat("Class Level Information\n")
	cat("Summary Information\n")
	printDataFrame(result)
	#cat("\n")
  if (length(respvar) == 1) { 
    numObsNM = length((na.omit(data[,respvar])))
    numObs = length(data[,respvar])
  } else {
    numObsNM = nrow((na.omit(data[,respvar])))
    numObs = nrow((data[,respvar]))
  }
	if (!is.null(respvar) && length(respvar) >= 1) {
		if (numObs == numObsNM) {
			cat("Number of Observations:", numObs,"\n")
		} else {
			cat("Number of Observations:", numObs,"\n")
			cat("Number of Non-Missing Observations:", numObsNM,"\n\n")
		}
	}
}

