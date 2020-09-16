import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class AddIntAnnotationAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull final AnActionEvent e) {
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();

        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        primaryCaret.selectWordAtCaret(true);
        String word = primaryCaret.getSelectedText();
        int start = primaryCaret.getSelectionStart();
        int end = primaryCaret.getSelectionEnd();

        WriteCommandAction.runWriteCommandAction(project, () ->
                document.replaceString(start, end, word + ": int")
        );

        primaryCaret.removeSelection();
    }

    @Override
    public void update(@NotNull final AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();

        primaryCaret.selectLineAtCaret();
        String line = primaryCaret.getSelectedText();
        primaryCaret.selectWordAtCaret(true);
        String word = primaryCaret.getSelectedText();
        primaryCaret.removeSelection();

        String[] lexemes = line.split(" ");

        Boolean isVar = false;

        // case of variable declaration
        if ((lexemes[0].equals(word) && lexemes[1].equals("=")) || lexemes[0].equals(word + "="))
            isVar = true;

        // case of function argument declaration
        if (lexemes[0].equals("def")){
            Boolean isOpenBracket = false;
            for (String s : lexemes) {
                if (s.contains("("))
                    isOpenBracket = true;

                if (s.contains(word) && isOpenBracket && !s.contains(word+":"))
                    isVar = true;

                if (s.contains(")"))
                    break;
            }
        }

        e.getPresentation().setEnabledAndVisible(
                project != null && editor != null && isVar
        );
    }
}