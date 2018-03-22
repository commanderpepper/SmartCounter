package humzaahmad.smartcounter.data.util

import humzaahmad.smartcounter.util.AppExecutors
import java.util.concurrent.Executor

/**
 * Created by Humza on 3/14/2018.
 */
class SingleExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { command -> command.run() }
    }
}