import Testing
import TokioUtil

@Suite("TokioUtil Export Smoke Tests")
struct TokioUtilExportTests {
    @Test("Swift module loads cleanly")
    func testSwiftModuleLoads() throws {
        #expect(true)
    }
}
