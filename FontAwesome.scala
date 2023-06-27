import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.`|`
import com.raquo.laminar.api.L._
import com.raquo.laminar.api.L.svg._
import typings.fortawesomeFontawesomeCommonTypes.mod._
import typings.fortawesomeFontawesomeSvgCore.{mod => fa}

object FontAwesome {
  def abstractElementToL(tree: fa.AbstractElement): SvgElement = {
    import com.raquo.domtypes.generic.codecs._

    customSvgTag(tree.tag)(
      tree.attributes.asInstanceOf[js.Dictionary[String | Double]]
        .filter({case (key, value) => key != "xmlns"})
        .map {
          case (key, value) => {
            customSvgAttr(key, StringAsIsCodec) := value.toString
          }
        }.toSeq,
      tree.children.fold(new js.Array[SvgElement])(_.map(abstractElementToL)),
    )
  }

  some new code here

  implicit def renderFontAwesomeIcon(icon: IconLookup): SvgElement =
    abstractElementToL(fa.icon(icon).`abstract`(0))

  def apply(icon: IconLookup): SvgElement = renderFontAwesomeIcon(icon)
}
