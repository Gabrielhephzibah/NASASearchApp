package com.cherish.apimodule.utils

import com.cherish.apimodule.domain.model.*
import com.cherish.apimodule.domain.model.Collection

object MilkyWayTest {
    val response = MilkyWay(
        Collection(
            listOf(
                Item(
                    listOf(
                        Data(
                            "GSFC",
                            "2017-12-08T00:00:00Z",
                            "Our Milky Way galaxy and its small companions are surrounded by a giant halo of million-degree gas (seen in blue in this artists' rendition) that is only visible to X-ray telescopes in space. University of Michigan astronomers discovered that this massive hot halo spins in the same direction as the Milky Way disk and at a comparable speed.",
                            "image",
                            "GSFC_20171208_Archive_e001362",
                            "A monster in the Milky Way"
                        )
                    ),
                    listOf(Link("https://images-assets.nasa.gov/image/GSFC_20171208_Archive_e001362/GSFC_20171208_Archive_e001362~thumb.jpg"))
                )
            )
        )
    )
}
