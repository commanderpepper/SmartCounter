package humzaahmad.smartcounter.addeditproject

import humzaahmad.smartcounter.BasePresenter
import humzaahmad.smartcounter.BaseView

interface AddEditProjectContract {
    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter
}