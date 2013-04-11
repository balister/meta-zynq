# linux-yocto-custom.bb:
#
#   Provides an example/minimal kernel recipe that uses the linux-yocto
#   and oe-core kernel classes to apply a subset of yocto kernel 
#   management to git managed kernel repositories.
#
# Notes:
#
#   kconfig(s): the kernel must be configured with a defconfig, or via
#               configuration fragment(s). Either of these can be added
#               via bbappend.
#   patches: patches can be merged into to the source git tree itself, added
#            using standard bbappend syntax or controlled via .scc feature 
#            descriptions (also via bbappends)
#   
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v3.4 only):
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition (for kernel v3.4 only):
#            SRC_URI += "file://feature.scc"
#
# Warning:
#
#   Building the sample kernel tree (kernel.org) without providing any
#   configuration will result in build or boot errors. This is not a bug
#   it is a required element for creating a valid kernel.
#    

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-zynq-git:"

SRC_URI = "git://git.xilinx.com/linux-xlnx.git;protocol=git;nocheckout=1 \
           file://zynq.dtsi \
           file://defconfig \
          "

SRC_URI_append_zynq-zc702 += "file://zc702-ram.dts"
SRC_URI_append_zynq-zc702 += "file://zc702-sd.dts"

KERNEL_IMAGETYPE = "uImage"
KERNEL_DEVICETREE_zynq-zc702 = "${WORKDIR}/zc702-ram.dts \
                                ${WORKDIR}/zc702-sd.dts"

LINUX_VERSION ?= "3.8"
LINUX_VERSION_EXTENSION ?= "-xilinx"

# tag: v3.4 76e10d158efb6d4516018846f60c2ab5501900bc
SRCREV="6a0bedad60e2bca8d9b50bf81b9895e29e31a6d7"

PE = "0"
PR = "${INC_PR}.0"
PV = "${LINUX_VERSION}+git${SRCPV}"

KERNEL_EXTRA_ARGS = "UIMAGE_LOADADDR=0x8000"

COMPATIBLE_MACHINE = "zynq-zc702"
