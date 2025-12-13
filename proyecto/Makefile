KOTLINC=kotlinc
SRCDIR=src
BUILDDIR=build
LIBS=lib/jlayer-1.0.1.jar:lib/pausablePlayer.jar

SRC=$(SRCDIR)/*.kt

all: compile

compile:
	$(KOTLINC) -cp "$(LIBS)" -d $(BUILDDIR) $(SRC)

run: compile
	kotlin -cp "$(BUILDDIR):$(LIBS)" proyecto.MainKt

clean:
	rm -rf $(BUILDDIR)/*

