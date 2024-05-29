package com.example.inventarapp_v2

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun getAllInventoryItems_returnsItems() {
        val responseBody = "[{\"id\":1,\"inventoryNumber\":\"001\",\"name\":\"Item 1\",\"acquisitionDate\":\"2020-01-01\",\"itemValue\":100.0,\"serialNumber\":\"SN12345\"}]"
        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        val response = apiService.getAllInventoryItems().execute()
        assertTrue(response.isSuccessful)
        assertNotNull(response.body())
        assertEquals(1, response.body()!!.size)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
