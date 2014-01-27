DESCRIPTION = "Test image for meta-kde plasma-active tests"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
export IMAGE_BASENAME = "plasma-active-test-image"

include recipes-core/images/core-image-core.bb

IMAGE_FEATURES += "package-management debug-tweaks"
IMAGE_INSTALL += "\
    packagegroup-kde-image \
    packagegroup-kde-base \
    packagegroup-kde-active \
    \
    oxygen-icons \
    oxygen-fonts \
    kde-wallpapers \
"
