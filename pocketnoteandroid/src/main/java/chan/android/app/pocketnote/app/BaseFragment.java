package chan.android.app.pocketnote.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import chan.android.app.pocketnote.app.db.NoteResource;
import chan.android.app.pocketnote.app.preferences.PreferenceResource;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

  protected PreferenceResource preferenceResource = ResourceFactory.Main.getSingleton().providePreferenceResource();

  protected NoteResource noteResource = ResourceFactory.Main.getSingleton().provideNoteResource();

  private CompositeSubscription compositeSubscription;

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    compositeSubscription = new CompositeSubscription();
  }

  /**
   * Convenient method to subscribe an subscription
   * @param subscription
   */
  public void subscribe(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  @Override
  public void onDestroy() {
    compositeSubscription.unsubscribe();
    super.onDestroy();
  }

  public NoteResource getNoteResource() {
    return noteResource;
  }

  public PreferenceResource getPreferenceResource() {
    return preferenceResource;
  }
}
