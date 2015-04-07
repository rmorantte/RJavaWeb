# --------------------------------------------------------       
# printWarningsFitDist: prints warning messages (if any)
# ARGUMENTS:
# warnMessages - warning messsages from function output
# --------------------------------------------------------

printWarningsFitDist <- function(warnMessages)
  UseMethod("printWarningsFitDist")

printWarningsFitDist.default <- function(warnMessages) {
  if (!is.null(warnMessages))
    cat("\nWarning(s):\n\n")
  for (i in 1:length(warnMessages)) {
    print(warnMessages[i])
  }  
}
