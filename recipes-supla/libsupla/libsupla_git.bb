DESCRIPTION = "SUPLA C Library"
HOMEPAGE = "https://github.com/QB4-dev/libsupla"
AUTHOR = "Qb4-dev"
SECTION = "libs"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/QB4-dev/libsupla.git;branch=master;protocol=https"
SRCREV = "19df786615dbe856f2da3f0b8b5ce7dc1edece29"

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
