package testLecture.code

object ProgramToCover {
  def methodToCover(c1: Boolean = false,
                    c2: Boolean = false,
                    c3: Boolean = false,
                    c4: Boolean = false,
                    c5: Boolean = false,
                    k: Int = 3): Unit ={
    print("A")
    if(c1 && c2) print("B") else print("C")
    print("D")
    if(c3 || c4) print("E") else print("F")
    print("G")
    if(c5) print("H")
    for(j <- 1 to k) print(j)
  }
}
