package pl.codinglab.bookpublisher.view.editbook;

import pl.codinglab.bookpublisher.model.BookRepository;
import pl.codinglab.bookpublisher.scopes.BookDialogScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

import javax.inject.Inject;

public class EditBookDialogViewModel implements ViewModel {

    public static final String CLOSE_DIALOG_NOTIFICATION = "CLOSE_DIALOG";

    @InjectScope
    private BookDialogScope dialogScope;

    @Inject
    private BookRepository repository;

    public void initialize() {
        dialogScope.subscribe(BookDialogScope.OK_BEFORE_COMMIT, (key, payload) ->
                applyAction());
    }

    private void applyAction() {
        dialogScope.publish(BookDialogScope.COMMIT);
        repository.save(dialogScope.bookToEditProperty().get());
        publish(CLOSE_DIALOG_NOTIFICATION);
    }
}