DESCRIPTION = "supla-device for Linux"
HOMEPAGE = "https://github.com/SUPLA/supla-device"
AUTHOR = "klew"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a23a74b3f4caf9616230789d94217acb"

SRC_URI = "git://github.com/SUPLA/supla-device.git;protocol=https;branch=main"
SRCREV = "1e2253d79f7f0ad002367e0ccf5b706137ba8d2d"
PV="25.11"

SRC_URI += "\
    file://supla-device.sh \
    file://supla-device.service \
    file://supla-device.yaml \
"

S = "${WORKDIR}/git"

inherit cmake systemd update-rc.d

DEPENDS = "openssl yaml-cpp"

# Tell CMake to use extras/examples/linux as the source directory
OECMAKE_SOURCEPATH = "${S}/extras/examples/linux"

# Export the variable expected by the project
export SUPLA_DEVICE_PATH = "${S}"

# Allow to use CMake FetchContent
do_configure[network] =  "1"
do_compile[network] = "1"
EXTRA_OECMAKE:append = "-DFETCHCONTENT_FULLY_DISCONNECTED=OFF"

# Suppress format errors - this should be fixed in source
TARGET_CFLAGS += "-Wno-error=format -Wno-format"
TARGET_CXXFLAGS += "-Wno-error=format -Wno-format"

do_install:append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/supla-device-linux ${D}${bindir}/supla-device-linux

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/supla-device.sh ${D}${sysconfdir}/init.d/supla-device
	
	install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/supla-device.service ${D}${systemd_system_unitdir}/supla-device.service
	
	install -d ${D}${sysconfdir}/
	install -m 0644 ${WORKDIR}/supla-device.yaml ${D}${sysconfdir}/supla-device.yaml
}

INITSCRIPT_NAME = "supla-device"
INITSCRIPT_PARAMS = "start 99 5 . stop 60 0 1 6 ."

SYSTEMD_SERVICE:${PN} = "apfd.service"
SYSTEMD_AUTO_ENABLE = "enable"

FILES:${PN} += "\
    ${systemd_system_unitdir}/supla-device.service \
"

CONFFILES:${PN} = "\
    ${sysconfdir}/supla-device.yaml \
"

