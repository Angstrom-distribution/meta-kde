LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=5c213a7de3f013310bd272cdb6eb7a24"

inherit kde_cmake kde_exports kde_without_docs kde_rdepends perlnative

DEPENDS = "kdelibs4-native automoc4-native soprano-native strigi libdbusmenu-qt soprano shared-desktop-ontologies dbus giflib attica jpeg libpng bzip2 libpcre perl-native aspell"

SRC_URI = "git://anongit.kde.org/kdelibs.git;branch=master \
    file://0001-Disable-doctools.patch \
    file://0002-Fix-openssl-check.patch \
    file://0003-Fix-FindKDE4Internals-cmake-file.patch \
    file://0004-Fix-the-path-to-Icemaker.patch \
    file://0006-Fix-makekdewidget-executable-path.patch \
    "

#     file://0003-Fix-FindKDE4Internals-cmake-file.patch \

## Tag v4.8.2
SRCREV = "d2ce1819baa85ad0e895971ca25232327e9fc91e"
PV = "4.8.2+git${SRCPV}"

S = "${WORKDIR}/git"

KDE_EXPORT_FILES = "${S}/build/CMakeFiles/Export/_usr/share/apps/cmake/modules/KDELibs4LibraryTargets-relwithdebinfo.cmake ${S}/build/KDELibsDependencies.cmake"

FILES_${PN} =+ "\
    ${libdir}/libkdeinit4_*.so \
    ${libdir}/kde4/*.so \
    ${libdir}/kde4/libexec/* \
    ${libdir}/kde4/plugins/designer/* \
    ${libdir}/kde4/plugins/imageformats/* \
    ${libdir}/kde4/plugins/kauth/* \
    ${libdir}/kde4/plugins/script/*.so.* \
    \
    ${datadir}/apps/* \
    ${datadir}/config/* \
    ${datadir}/mime/* \
    ${datadir}/kde4/* \
    ${datadir}/icons/* \
    ${datadir}/dbus-1/* \
    \
    ${sysconfdir}/* \
        "

FILES_${PN}-dev += "\
    ${bindir}/kconfig_compiler \
    ${bindir}/kde4-config \
    \
    ${datadir}/apps/cmake/* \
    \
    ${libdir}/kde4/plugins/script/libkrossqtsplugin.so \
    "

FILES_${PN}-dbg += "\
    ${libdir}/kde4/.debug/* \
    ${libdir}/kde4/*/.debug/* \
    ${libdir}/kde4/*/*/.debug/* \
    ${libdir}/kde4/*plugins/kauth/helper/.debug/* \
    "

# kdelibs *must* be built out of tree
OECMAKE_SOURCEPATH = ".."
OECMAKE_BUILDPATH = "build"

# OE_CROSSCOMPILING is only set for applications that use kde, but not for kdelibs itself.
# This will prevent errors when the cmake macro _set_fancy is required by other cmake files while compiling kdelibs.
EXTRA_OECMAKE =+ "\
    -DKJS_FORCE_DISABLE_PCRE=TRUE \
    -DSTRIGI_REQUIRED=FALSE \
    \
    -DICEMAKER_EXECUTABLE=${STAGING_BINDIR_NATIVE}/icemaker \
    \
    -DPERL_LIBDIR=${STAGING_LIBDIR}/perl \
    -DBZIP2_NEED_PREFIX=TRUE \
    \
    -DKDE4_INSTALL_DIR=${D}${prefix} \
    -DOE_CROSSCOMPILING=FALSE \
    "

# This will cause errors related to disabled DEPRECATED settings (e.g. usr/include/nepomuk/tools.h will be missing but is required)
##  -DKDE_PLATFORM_PROFILE=Mobile \
