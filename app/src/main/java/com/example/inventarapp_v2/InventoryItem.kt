package com.example.inventarapp_v2

data class InventoryItem(
    val id: Long?,
    val inventoryNumber: String,
    val name: String,
    val acquisitionDate: String,
    val itemValue: Double,
    val serialNumber: String
)
