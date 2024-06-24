package lt.viko.eif.l.jurgutis.editor_REST.assembler;

import lt.viko.eif.l.jurgutis.editor_REST.controller.EditorController;
import lt.viko.eif.l.jurgutis.editor_REST.model.Editor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EditorAssembler implements RepresentationModelAssembler<Editor, EntityModel<Editor>> {
    public String Ref;

    public List<EntityModel<Editor>> toModelList(List<Editor> editors) {
        return editors.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Override
    public EntityModel<Editor> toModel(Editor editor) {
        return EntityModel.of(editor,
                !Objects.equals(Ref, "All") ? linkTo(methodOn(EditorController.class).getAllEditors()).withRel("Get all editors")
                        : linkTo(methodOn(EditorController.class).getAllEditors()).withSelfRel(),
                !Objects.equals(Ref, "findID") ? linkTo(methodOn(EditorController.class).getEditorById(editor.getId())).withRel("Find editor")
                        : linkTo(methodOn(EditorController.class).getEditorById(editor.getId())).withSelfRel(),
                !Objects.equals(Ref, "New") ? linkTo(methodOn(EditorController.class).createEditor(editor)).withRel("New editor")
                        : linkTo(methodOn(EditorController.class).createEditor(editor)).withSelfRel(),
                !Objects.equals(Ref, "Delete") ? linkTo(methodOn(EditorController.class).deleteEditor(editor.getId())).withRel("Delete editor")
                        : linkTo(methodOn(EditorController.class).deleteEditor(editor.getId())).withSelfRel(),
                !Objects.equals(Ref, "Update") ? linkTo(methodOn(EditorController.class).updateEditor(editor.getId(), editor)).withRel("Update editor")
                        : linkTo(methodOn(EditorController.class).updateEditor(editor.getId(), editor)).withSelfRel());
    }
}
