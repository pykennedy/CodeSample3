package pyk.codesample3

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pyk.codesample3.tests.DetailsFragmentTest
import pyk.codesample3.tests.ListFragmentTest

@RunWith(Suite::class)
@Suite.SuiteClasses(ListFragmentTest::class, DetailsFragmentTest::class)
class AndroidTestSuite {

}