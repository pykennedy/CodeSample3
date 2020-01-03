package pyk.codesample3

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pyk.codesample3.tests.AboutFragmentTest
import pyk.codesample3.tests.DetailsFragmentTest
import pyk.codesample3.tests.ListFragmentTest
import pyk.codesample3.tests.SpinFragmentTest

@RunWith(Suite::class)
@Suite.SuiteClasses(ListFragmentTest::class, DetailsFragmentTest::class, SpinFragmentTest::class,
                    AboutFragmentTest::class) class AndroidTestSuite {}