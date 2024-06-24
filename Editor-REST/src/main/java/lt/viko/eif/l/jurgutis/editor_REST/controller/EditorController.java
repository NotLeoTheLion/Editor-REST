package lt.viko.eif.l.jurgutis.editor_REST.controller;

import io.swagger.v3.oas.annotations.Operation;
import lt.viko.eif.l.jurgutis.editor_REST.assembler.EditorAssembler;
import lt.viko.eif.l.jurgutis.editor_REST.model.Editor;
import lt.viko.eif.l.jurgutis.editor_REST.repos.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editors")
public class EditorController {

    @Autowired
    private EditorRepository editorRepository;

    @Autowired
    private EditorAssembler editorAssembler;

    @Operation(summary = "Get all editors")
    @GetMapping
    public CollectionModel<EntityModel<Editor>> getAllEditors() {
        List<Editor> editors = editorRepository.findAll();
        return CollectionModel.of(editorAssembler.toModelList(editors));
    }

    @Operation(summary = "Get editor by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Editor>> getEditorById(@PathVariable(value = "id") int editorId) {
        Optional<Editor> editor = editorRepository.findById(editorId);
        if (editor.isPresent()) {
            return ResponseEntity.ok().body(editorAssembler.toModel(editor.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create new editor")
    @PostMapping
    public ResponseEntity<EntityModel<Editor>> createEditor(@RequestBody Editor editor) {
        Editor savedEditor = editorRepository.save(editor);
        return ResponseEntity.ok().body(editorAssembler.toModel(savedEditor));
    }

    @Operation(summary = "Update editor")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Editor>> updateEditor(@PathVariable(value = "id") int editorId, @RequestBody Editor editorDetails) {
        Optional<Editor> editor = editorRepository.findById(editorId);
        if (editor.isPresent()) {
            Editor updatedEditor = editor.get();
            updatedEditor.setFirstname(editorDetails.getFirstname());
            updatedEditor.setLastname(editorDetails.getLastname());
            updatedEditor.setEmail(editorDetails.getEmail());
            updatedEditor.setSkillLevel(editorDetails.getSkillLevel());
            editorRepository.save(updatedEditor);
            return ResponseEntity.ok().body(editorAssembler.toModel(updatedEditor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete editor")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEditor(@PathVariable(value = "id") int editorId) {
        Optional<Editor> editor = editorRepository.findById(editorId);
        if (editor.isPresent()) {
            editorRepository.delete(editor.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
