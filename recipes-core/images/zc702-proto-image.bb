DESCRIPTION = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "splash ssh-server-openssh tools-sdk \
                   tools-debug debug-tweaks package-management"

CORE_IMAGE_EXTRA_INSTALL = "\
    kernel-modules \
    "

inherit core-image
