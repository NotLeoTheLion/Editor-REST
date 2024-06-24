package lt.viko.eif.l.jurgutis.editor_REST.service;

import lt.viko.eif.l.jurgutis.editor_REST.exception.ResourceNotFoundException;
import lt.viko.eif.l.jurgutis.editor_REST.model.Editor;
import lt.viko.eif.l.jurgutis.editor_REST.repos.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorService {
    @Autowired
    private EditorRepository editorRepository;

    public List<Editor> getAllEditors() {
        return editorRepository.findAll();
    }

    public Optional<Editor> getEditorById(int editorId) {
        return Optional.ofNullable(editorRepository.findById(editorId)
                .orElseThrow(() -> new ResourceNotFoundException("Editor not found for this id :: " + editorId)));
    }

    public Editor createEditor(Editor editor) {
        return editorRepository.save(editor);
    }

    public Optional<Editor> updateEditor(int editorId, Editor editorDetails) {
        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new ResourceNotFoundException("Editor not found for this id :: " + editorId));

        editor.setFirstname(editorDetails.getFirstname());
        editor.setLastname(editorDetails.getLastname());
        editor.setEmail(editorDetails.getEmail());
        editor.setSkillLevel(editorDetails.getSkillLevel());
        return Optional.of(editorRepository.save(editor));
    }

    public void deleteEditor(int editorId) {
        Editor editor = editorRepository.findById(editorId)
                .orElseThrow(() -> new ResourceNotFoundException("Editor not found for this id :: " + editorId));
        editorRepository.delete(editor);
    }
}
