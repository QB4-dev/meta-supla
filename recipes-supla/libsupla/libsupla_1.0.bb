DESCRIPTION = "SUPLA C Library"
HOMEPAGE = "https://github.com/QB4-dev/libsupla"
AUTHOR = "Qb4-dev"
SECTION = "libs"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/QB4-dev/libsupla.git;branch=master;protocol=https"
SRCREV = "43cff30bed3059cc42292ff8026bd6982260a0db"

S = "${WORKDIR}/git"

DEPENDS += "openssl"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR=${D}
}

FILES:${PN} += "\
    /usr/lib/* \
    /usr/include/libsupla/* \
"
