DESCRIPTION = "Test image for meta-kde plasma-active tests"

export IMAGE_BASENAME = "plasma-active-test-image"

require recipes-core/images/core-image-core.bb

IMAGE_FEATURES += "package-management debug-tweaks"

IMAGE_INSTALL += "\
    task-kde-image \
    task-kde-base \
    task-kde-active \
"

LICENSE = "MIT"