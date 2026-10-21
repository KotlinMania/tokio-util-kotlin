import Testing
import TokioUtil

@Suite("TokioUtil Export Smoke Tests")
struct TokioUtilExportTests {
    @Test("CancellationToken creation and cancellation")
    func testCancellationToken() throws {
        let token = ExportedKotlinPackages.io.github.kotlinmania.tokioutil.sync.cancellationtoken.CancellationToken()
        #expect(!token.isCancelled())

        let child = token.childToken()
        #expect(!child.isCancelled())

        token.cancel()
        #expect(token.isCancelled())
        #expect(child.isCancelled())
    }

    @Test("BytesCodec instantiation")
    func testBytesCodec() throws {
        let codec = ExportedKotlinPackages.io.github.kotlinmania.tokioutil.codec.BytesCodec()
        #expect(codec != nil)
    }
}
