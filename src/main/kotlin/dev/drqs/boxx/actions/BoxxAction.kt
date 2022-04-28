package dev.drqs.boxx.actions

import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.markup.EffectType
import com.intellij.openapi.editor.markup.HighlighterTargetArea
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color
import java.awt.Font

class BoxxAction : AnAction() {

    var active = true

//    override fun update(e: AnActionEvent) {
//         super.update(e)
//    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = DataManager.getInstance().getDataContext().getData(PlatformDataKeys.EDITOR)
        if (editor !is Editor) return
        val mm = editor.markupModel

        if (active) {

            var h = 0.0f

            for (region in editor.foldingModel.allFoldRegions) {
                val s = region.startOffset
                val e = region.endOffset

                mm.addRangeHighlighter(
                    s, e, 9999,
                    TextAttributes(
                        null,
                        Color.getHSBColor(h, 0.2f, 0.2f),
                        Color.getHSBColor(h, .7f, 0.4f),
                        EffectType.ROUNDED_BOX,
                        Font.PLAIN
                    ),
                    HighlighterTargetArea.EXACT_RANGE
                )

                h += 0.1f
                if (h > 1.0f) {
                    h -= 1.0f
                }
            }
        } else {
            mm.removeAllHighlighters();
        }

        active = !active
    }
}