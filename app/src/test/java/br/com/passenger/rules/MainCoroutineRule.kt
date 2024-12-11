package br.com.passenger.rules
//
// import kotlinx.coroutines.ExperimentalCoroutinesApi
// import kotlinx.coroutines.test.TestDispatcher
// import kotlinx.coroutines.test.UnconfinedTestDispatcher
// import org.junit.rules.TestWatcher
//
// @ExperimentalCoroutinesApi
// class MainCoroutineRule(
//    val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
// ) : TestWatcher() {
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//    }
// }
//
// class MainDispatcherRule(
//    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
// ) : TestWatcher() {
//    override fun starting(description: Description) {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description) {
//        Dispatchers.resetMain()
//    }
// }
