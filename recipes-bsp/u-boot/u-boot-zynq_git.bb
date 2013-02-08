require recipes-bsp/u-boot/u-boot.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-zynq-git:"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

#FILESDIR = "${@os.path.dirname(d.getVar('FILE',1))}/u-boot-git/${MACHINE}"

# We use the revision in order to avoid having to fetch it from the
# repo during parse
SRCREV = "26786228acfdc0a02190a8d9ca9fcca51a5dcf28"

PV = "xilinx-zc702"
PR = "r1"

SRC_URI = "git://git.xilinx.com/u-boot-xlnx.git;protocol=git \
           file://0001-Change-boot-options-so-linux-can-boot-without-a-ramd.patch \
"


S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_deploy_append() {
	install ${S}/u-boot ${DEPLOYDIR}/u-boot-${MACHINE}.elf
}
