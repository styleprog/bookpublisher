package pl.codinglab.bookpublisher.view.toolbar;

import pl.codinglab.bookpublisher.scopes.BookDialogScope;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewModel;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class ToolbarViewModel implements ViewModel {

    @Inject
    private Instance<BookDialogScope> scopeInstance;

    public List<Scope> getScopesForAddDialog() {
        return Collections.singletonList(scopeInstance.get());
    }
}