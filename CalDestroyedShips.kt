/**
 * You can edit, run, and share this code. 
 * play.kotlinlang.org 
 */

fun main() {
    var input = intArrayOf(3,6,2,7,5)
    var days = 0
    
    //println("Total Ships ${input.asList()}")                             //Remove comment to see extra information
    var result = calDestroyedShips(input, 0)
    while(result.size != input.size){
        days++
        input = result
        //println("Ships left After $days Day ${result.asList()}")        //Remove comment to see extra information
        result = calDestroyedShips(input, 0)
    }
    println("Number of days after which no ships are destroyed : ${days}") 
}

/**
 *   Function to destroy ships per day
 */

private fun calDestroyedShips(input: IntArray, index: Int): IntArray{
    val secLastIndex = input.size - 1
    var arr = input
    for(i in index until input.size){
        if(i < secLastIndex){
            if(input[i+1] > input[i]){
                //Used recurssion
                arr = calDestroyedShips(input.removeAtByUsingLoop(i+1), i+1)
                break
            }
        }
    }
    return arr
}

/**
 * 
 *    Bellow 4 functions are different approches to remove an element from an Int Array
 *    These functions are created as Extension function (A useful feature of Kotlin)   
 * 
 */ 


//Remove element by using loop iteration 
//I think this one is the best way but it also require more code
private fun IntArray.removeAtByUsingLoop(index: Int): IntArray{
    if (index < 0 || index >= this.size) {
        return this
    }
    val result = IntArray(this.size - 1)
    for (i in 0 until index) {
        result[i] = this[i]
    }
    for (i in index until this.size - 1) {
        result[i] = this[i + 1]
    }
    return result
}

//Remove element by converting the array in a list then removing the element and again converting the list into Array
private fun IntArray.removeAtByUsingList(index: Int): IntArray{
    if (index < 0 || index >= this.size) {
        return this
    }

    val result = this.toMutableList()
    result.removeAt(index)
    return result.toIntArray()
}

//Remove element by using Filter and Map functions of kotlin
private fun IntArray.removeAtByUsingFilterAndMap(index: Int): IntArray{
    return if (index < 0 || index >= this.size) {
        this
    } else (this.indices)
        .filter { i: Int -> i != index }
        .map { i: Int -> this[i] }
        .toIntArray()
}

//Remove element by using System.arraycopy 
//Note* I just found out about this method on the Internet but I already knew about the above 3 approches
private fun IntArray.removeAtByUsingSystemArrayCopy(index: Int): IntArray{
    if (index < 0 || index >= this.size) {
        return this
    }

    val result = IntArray(this.size - 1)
    System.arraycopy(this, 0, result, 0, index)
    System.arraycopy(this, index + 1, result, index, this.size - index - 1)
    return result
} 
