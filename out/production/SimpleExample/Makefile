#this is the makefile for GC class

##### ISWIN= $(shell uname | grep CYGWIN)
###file separator and classpath separator
#unix and mac users
SEP=/
PSEP=:
#windows cygwin users
#SEP=\\
#PSEP=\;
#windows users
#SEP=\
#PSEP=;

RM=rm -f
TAR=tar
JAVA=java
JAVAC=javac
#DEBUGFLAGS= -g -Xcheck:jni:all 

JARLIBHOME=.$(SEP)lib
CLASSPATH=$(JARLIBHOME)$(SEP)jar$(SEP)*

JAVAFLAGS=-cp $(CLASSPATH)$(PSEP)bin $(DEBUGFLAGS)
JAVACFLAGS= -d bin -sourcepath src -cp $(CLASSPATH)$(PSEP). -encoding utf-8 -Xlint:deprecation

SRC=SimpleExampleBase.java SimpleExample.java Plane.java Object3D.java ImageLoader.java Shader.java DotImage.java TextureImage.java TextureImageUtil.java
SRCINDIR=$(SRC:%=src$(SEP)%)

.PHONY: clean cleanall remote tar
.SUFFIXES : .java .class

ALL::   bin$(SEP)SimpleExample.class
	$(JAVA) $(JAVAFLAGS) $(<:bin$(SEP)%.class=%) 

bin$(SEP)SimpleExample.class: $(SRCINDIR)
	mkdir -p bin
	$(JAVAC) $(JAVACFLAGS) $(@:bin$(SEP)%.class=src$(SEP)%.java)

clean:
	find . -name "._*" -exec $(RM) {} \;
	$(RM) -R bin

cleanall:
	find . -name "._*" -exec $(RM) {} \;
	$(RM) -R bin javadoc

tar:
	$(RM) -R bin javadoc
	cd ..; $(TAR) zcvf simpleexample.tar.gz SimpleExample

remote: bin/SimpleExample.class
#	DISPLAY=localhost:0.0;$(JAVA) $(<:bin/%.class=%) &
	DISPLAY=:0.0;$(JAVA) $(JAVAFLAGS) $(<:bin/%.class=%) &
	sleep 5
#       DISPLAY=localhost:0.0;xwd -name simpleJoglShader -out t
#       DISPLAY=localhost:0.0;xwd -root -out t
	DISPLAY=:0.0;xwd -name 3DCGSimpleExample -out t
	xwud < t
	rm -f t
	killall java
