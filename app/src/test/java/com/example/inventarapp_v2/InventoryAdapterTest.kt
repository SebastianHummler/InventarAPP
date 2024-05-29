package com.example.inventarapp_v2

class InventoryAdapterTest {

    private lateinit var adapter: InventoryAdapter
    private lateinit var items: List<InventoryItem>

    @Before
    fun setUp() {
        items = listOf(InventoryItem(1, "001", "Item 1", "2020-01-01", 100.0, "SN12345"))
        adapter = InventoryAdapter(items)
    }

    @Test
    fun itemCount() {
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun bindViewHolder() {
        val viewHolder = Mockito.mock(InventoryAdapter.ViewHolder::class.java)
        adapter.onBindViewHolder(viewHolder, 0)
        verify(viewHolder).bind(items[0])
    }

    @Test
    fun updateData() {
        val newItems = listOf(InventoryItem(2, "002", "Item 2", "2020-02-02", 200.0, "SN12346"))
        adapter.updateData(newItems)
        assertEquals(1, adapter.itemCount)
        assertEquals(newItems[0], adapter.items[0])
    }
}
