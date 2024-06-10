package features.home.components.sidebar

import musaki.composeapp.generated.resources.Res
import musaki.composeapp.generated.resources.star
import org.jetbrains.compose.resources.DrawableResource


data class SideBarListItem(
    val resource: DrawableResource,
    val title: String
)

val musakiSideBarList = List(30) {
    SideBarListItem(
        resource = Res.drawable.star,
        title = "Item $it"
    )
}