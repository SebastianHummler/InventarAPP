package com.example.inventarapp_v2

class MainActivityTest {

    @Test
    fun activityLaunches() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                assertNotNull(activity.findViewById(R.id.recyclerView))
            }
        }
    }

    @Test
    fun recyclerViewPopulatesCorrectly() {
        // Mock des ApiService, um vordefinierte Daten zurückzugeben
        val mockApiService = mock(ApiService::class.java)
        val expectedItems = listOf(InventoryItem(1, "001", "Item 1", "2020-01-01", 100.0, "SN12345"))
        `when`(mockApiService.getAllInventoryItems()).thenReturn(Call.success(expectedItems))

        // Starte MainActivity mit einem gemockten ApiService
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val recyclerView = activity.findViewById<RecyclerView>(R.id.recyclerView)
                assertTrue(recyclerView.adapter?.itemCount == 1)
            }
        }
    }
}
