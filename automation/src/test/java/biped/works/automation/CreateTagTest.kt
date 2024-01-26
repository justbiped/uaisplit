package biped.works.automation

import java.util.Base64
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.Test

class CreateTagTest {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Test
    fun testetst() {
        val commandArray = arrayOf(
            "git echo \"version.major=bla\nversion.minor=blabla\" >> ~/Projects/biped/uaisplit/version.properties",
//            "git add version.properties",
//            "git commit -m \"Testando\"",
//            "git push origin master"
        )
        print(Base64.getEncoder().encodeToString("version.major=1\nversion.minor=0\nversion.patch=1".toByteArray()))
        Runtime.getRuntime().exec(commandArray)
    }
}