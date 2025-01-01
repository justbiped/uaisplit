package biped.works.transaction.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TransactionUiModelTest {

    @Test
    fun `invert incoming transaction to debt`() {
        val uiModel = TransactionUiModel(amount = 10.0)

        val debtUiModel = uiModel.invertAmount()

        assertThat(debtUiModel.amount).isEqualTo(-10.0)
    }

    @Test
    fun `invert debt transaction to incoming`() {
        val uiModel = TransactionUiModel(amount = -10.0)

        val debtUiModel = uiModel.invertAmount()

        assertThat(debtUiModel.amount).isEqualTo(10.0)
    }
}