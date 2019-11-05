/**
 * The ScreenSpec Interface. establishes the framework for all screens.
 *
 * @author Sean Lamont
 * @brief The ScreenSpec Interface. establishes the framework for all screens.
 * @date 10/8/19
 */
package lamont;
// conflict between google format and CheckStyle.

/** Create an interface called ScreenSpec. This will define 3 methods: */
public interface ScreenSpec {

  // public String getResolution();
  String getResolution();

  // public int getRefreshRate();
  int getRefreshRate();

  // public int getResponseTime();
  int getResponseTime();
}
