package com.waicool20.cvauto.core

import com.waicool20.cvauto.util.matching.ITemplateMatcher
import java.awt.Rectangle
import java.awt.image.BufferedImage

class CachedRegion<T : IDevice>(
    val region: Region<T>
) : Region<T>(
    region.x,
    region.y,
    region.width,
    region.height,
    region.device,
    region.screen
) {
    private val cachedImage = region.capture()
    override fun capture(): BufferedImage = cachedImage

    override fun mapRectangleToRegion(rect: Rectangle): Region<T> =
        region.mapRectangleToRegion(rect)

    override fun mapFindResultToRegion(result: ITemplateMatcher.FindResult): RegionFindResult<T> =
        region.mapFindResultToRegion(result)

    override fun click(random: Boolean) = region.click()

    override fun type(text: String) = region.type(text)
}

fun <T: IDevice> Region<T>.asCachedRegion(): CachedRegion<T> = CachedRegion(this)