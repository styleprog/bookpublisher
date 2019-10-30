package pl.codinglab.bookpublisher.scopes;

import pl.codinglab.bookpublisher.model.Book;
import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MasterDetailScope implements Scope {

    private ObjectProperty<Book> selectedElement = new SimpleObjectProperty<>(this, "selectedBook");


    public ObjectProperty<Book> selectedElementProperty() {
        return selectedElement;
    }
}
