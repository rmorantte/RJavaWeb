### R code from vignette source 'doBy.Rnw'
### Encoding: ISO8859-1

###################################################
### code chunk number 1: doBy.Rnw:35-39
###################################################
dir.create("figures")
oopt <- options()
options("digits"=4, "width"=80, "prompt"=" ", "continue"="  ")
options(useFancyQuotes="UTF-8")


###################################################
### code chunk number 2: doBy.Rnw:61-62
###################################################
library(doBy)


###################################################
### code chunk number 3: doBy.Rnw:79-84
###################################################
data(CO2)
CO2 <- transform(CO2, Treat=Treatment, Treatment=NULL)
levels(CO2$Treat) <- c("nchil","chil")
levels(CO2$Type)  <- c("Que","Mis")
CO2 <- subset(CO2, Plant %in% c("Qn1", "Qc1", "Mn1", "Mc1"))


###################################################
### code chunk number 4: doBy.Rnw:96-97
###################################################
airquality <- subset(airquality, Month %in% c(5,6))


###################################################
### code chunk number 5: doBy.Rnw:125-128
###################################################
myfun1 <- function(x){c(m=mean(x), v=var(x))}
summaryBy(conc+uptake~Plant, data=CO2,
FUN=myfun1)


###################################################
### code chunk number 6: doBy.Rnw:139-141
###################################################
myfun2 <- function(x){c(mean(x), var(x))}
summaryBy(conc+uptake~Plant, data=CO2,FUN=myfun2)


###################################################
### code chunk number 7: doBy.Rnw:153-154
###################################################
summaryBy(uptake~Plant, data=CO2, FUN=c(mean,var,median))


###################################################
### code chunk number 8: doBy.Rnw:159-161
###################################################
mymed <- function(x)c(med=median(x))
summaryBy(uptake~Plant, data=CO2, FUN=c(mean,var,mymed))


###################################################
### code chunk number 9: doBy.Rnw:204-205
###################################################
summaryBy(conc+uptake~Plant, data=CO2, FUN=myfun1, id=~Type+Treat)


###################################################
### code chunk number 10: doBy.Rnw:216-218
###################################################
summaryBy(log(uptake)+I(conc+uptake)+ conc+uptake~Plant, data=CO2,
FUN=myfun1)


###################################################
### code chunk number 11: doBy.Rnw:225-227
###################################################
summaryBy(log(uptake)+I(conc+uptake)~Plant, data=CO2, p2d=TRUE,
FUN=myfun1)


###################################################
### code chunk number 12: doBy.Rnw:244-246
###################################################
summaryBy(log(uptake)+I(conc+uptake)+. ~Plant, data=CO2,
FUN=myfun1)


###################################################
### code chunk number 13: doBy.Rnw:257-259
###################################################
summaryBy(log(uptake) ~Plant+., data=CO2,
FUN=myfun1)


###################################################
### code chunk number 14: doBy.Rnw:268-270
###################################################
summaryBy(log(uptake) ~ 1, data=CO2,
FUN=myfun1)


###################################################
### code chunk number 15: doBy.Rnw:282-284
###################################################
summaryBy(conc+uptake+log(uptake)~Plant,
data=CO2, FUN=mean, id=~Type+Treat, keep.names=TRUE)


###################################################
### code chunk number 16: doBy.Rnw:297-298
###################################################
x<-orderBy(~Temp+Month, data=airquality)


###################################################
### code chunk number 17: doBy.Rnw:302-303
###################################################
head(x)


###################################################
### code chunk number 18: doBy.Rnw:309-311
###################################################
x<-orderBy(~-Temp+Month, data=airquality)
head(x)


###################################################
### code chunk number 19: doBy.Rnw:322-324
###################################################
x<-splitBy(~Month, data=airquality)
x


###################################################
### code chunk number 20: doBy.Rnw:330-331
###################################################
x[['5']]


###################################################
### code chunk number 21: doBy.Rnw:336-337
###################################################
attr(x,"groupid")


###################################################
### code chunk number 22: doBy.Rnw:347-348
###################################################
sampleBy(~1, frac=0.5, data=airquality)


###################################################
### code chunk number 23: doBy.Rnw:354-355
###################################################
sampleBy(~Month, frac=0.2, data=airquality,systematic=T)


###################################################
### code chunk number 24: doBy.Rnw:366-367
###################################################
subsetBy(~Month, subset=Wind>mean(Wind), data=airquality)


###################################################
### code chunk number 25: doBy.Rnw:380-382
###################################################
transformBy(~Month, data=airquality, minW=min(Wind), maxW=max(Wind),
    chg=sum(range(Wind)*c(-1,1)))


###################################################
### code chunk number 26: doBy.Rnw:396-400
###################################################
data(dietox)
dietox <- orderBy(~Pig+Time, data=dietox)
v<-lapplyBy(~Pig, data=dietox, function(d) c(NA, diff(d$Weight)/diff(d$Feed)))
dietox$FE <- unlist(v)


###################################################
### code chunk number 27: doBy.Rnw:405-409
###################################################
dietox <- orderBy(~Pig+Time, data=dietox)
wdata <- splitBy(~Pig, data=dietox)
v <- lapply(wdata, function(d) c(NA, diff(d$Weight)/diff(d$Feed)))
dietox$FE <- unlist(v)


###################################################
### code chunk number 28: doBy.Rnw:423-427
###################################################
data(airquality)
airquality <- transform(airquality, Month=factor(Month))
m<-lm(Ozone~Month*Wind, data=airquality)
coefficients(m)


###################################################
### code chunk number 29: doBy.Rnw:444-450
###################################################
Lambda <- rbind(
  c(0,-1,0,0,0,0,-10,0,0,0),
  c(0,1,-1,0,0,0,10,-10,0,0),
  c(0,0,1,-1,0,0,0,10,-10,0),
  c(0,0,0,1,-1,0,0,0,10,-10)
  )


###################################################
### code chunk number 30: doBy.Rnw:454-455
###################################################
esticon(m, Lambda)


###################################################
### code chunk number 31: doBy.Rnw:465-471
###################################################
Lambda <- rbind(
  c(0,0,0,0,0,0,1,0,0,0),
  c(0,0,0,0,0,0,0,1,0,0),
  c(0,0,0,0,0,0,0,0,1,0),
  c(0,0,0,0,0,0,0,0,0,1)
  )


###################################################
### code chunk number 32: doBy.Rnw:475-476
###################################################
esticon(m, Lambda, joint.test=T)


###################################################
### code chunk number 33: doBy.Rnw:516-519
###################################################
x <- c(1,1,1,2,2,2,1,1,1,3)
firstobs(x)
lastobs(x)


###################################################
### code chunk number 34: doBy.Rnw:524-526
###################################################
firstobs(~Plant, data=CO2)
lastobs(~Plant, data=CO2)


###################################################
### code chunk number 35: doBy.Rnw:535-538
###################################################
x <- c(1:4,0:5,11,NA,NA)
which.maxn(x,3)
which.minn(x,5)


###################################################
### code chunk number 36: doBy.Rnw:547-552
###################################################
x <- c(1,1,2,2,2,1,1,3,3,3,3,1,1,1)
subSeq(x)
subSeq(x, item=1)
subSeq(letters[x])
subSeq(letters[x],item="a")


###################################################
### code chunk number 37: doBy.Rnw:560-564
###################################################
x <- c("dec","jan","feb","mar","apr","may")
src1 <- list(c("dec","jan","feb"), c("mar","apr","may"))
tgt1 <- list("winter","spring")
recodeVar(x,src=src1,tgt=tgt1)


###################################################
### code chunk number 38: doBy.Rnw:571-573
###################################################
head(renameCol(CO2, 1:2, c("kk","ll")))
head(renameCol(CO2, c("Plant","Type"), c("kk","ll")))


###################################################
### code chunk number 39: doBy.Rnw:581-583
###################################################
#yvar <- c(0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0)
yvar <- c(0,0,0,1,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0)


###################################################
### code chunk number 40: doBy.Rnw:590-592
###################################################
#tvar <- seq_along(yvar) + c(0.1,0.2,0.3)
tvar <- seq_along(yvar) + c(0.1,0.2)


###################################################
### code chunk number 41: doBy.Rnw:597-598
###################################################
tse<- timeSinceEvent(yvar,tvar)


###################################################
### code chunk number 42: doBy.Rnw:613-618
###################################################
plot(sign.tse~tvar, data=tse, type="b")
grid()
rug(tse$tvar[tse$yvar==1], col='blue',lwd=4)
points(scale(tse$run), col=tse$run, lwd=2)
lines(abs.tse+.2~tvar, data=tse, type="b",col=3)


###################################################
### code chunk number 43: doBy.Rnw:622-627
###################################################
plot(tae~tvar, data=tse, ylim=c(-6,6),type="b")
grid()
lines(tbe~tvar, data=tse, type="b", col='red')
rug(tse$tvar[tse$yvar==1], col='blue',lwd=4)
lines(run~tvar, data=tse, col='cyan',lwd=2)


###################################################
### code chunk number 44: doBy.Rnw:631-635
###################################################
plot(ewin~tvar, data=tse,ylim=c(1,4))
rug(tse$tvar[tse$yvar==1], col='blue',lwd=4)
grid()
lines(run~tvar, data=tse,col='red')


###################################################
### code chunk number 45: doBy.Rnw:641-642
###################################################
tse$tvar[tse$abs<=1]


###################################################
### code chunk number 46: doBy.Rnw:649-652
###################################################
lynx <- as.numeric(lynx)
tvar <- 1821:1934
plot(tvar,lynx,type='l')


###################################################
### code chunk number 47: doBy.Rnw:658-662
###################################################
yyy <- lynx>mean(lynx)
head(yyy)
sss <- subSeq(yyy,TRUE)
sss


###################################################
### code chunk number 48: doBy.Rnw:666-668
###################################################
plot(tvar,lynx,type='l')
rug(tvar[sss$midpoint],col='blue',lwd=4)


###################################################
### code chunk number 49: doBy.Rnw:673-676
###################################################
yvar <- rep(0,length(lynx))
yvar[sss$midpoint] <- 1
str(yvar)


###################################################
### code chunk number 50: doBy.Rnw:680-682
###################################################
tse <- timeSinceEvent(yvar,tvar)
head(tse,20)


###################################################
### code chunk number 51: doBy.Rnw:688-691
###################################################
len1 <- tapply(tse$ewin, tse$ewin, length)
len2 <- tapply(tse$run, tse$run, length)
c(median(len1),median(len2),mean(len1),mean(len2))


###################################################
### code chunk number 52: doBy.Rnw:696-699
###################################################
tse$lynx <- lynx
tse2 <- na.omit(tse)
plot(lynx~tae, data=tse2)


###################################################
### code chunk number 53: doBy.Rnw:703-706
###################################################
plot(tvar,lynx,type='l',lty=2)
mm <- lm(lynx~tae+I(tae^2)+I(tae^3), data=tse2)
lines(fitted(mm)~tvar, data=tse2, col='red')


###################################################
### code chunk number 54: doBy.Rnw:719-720
###################################################
options(oopt)


###################################################
### code chunk number 55: doBy.Rnw:735-736
###################################################
CO2


###################################################
### code chunk number 56: doBy.Rnw:741-742
###################################################
head(airquality, n=20)


