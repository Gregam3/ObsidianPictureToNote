import java.io.File

fun main(args: Array<String>) {
    val originTag = if (args.isNotEmpty()) args[0] else null

    // Read every image in current folder
    val picturesInFolder = File(".").listFiles()!!.filter {
        it.isFile
                && !it.name.endsWith(".md")
                && !it.name.endsWith(".bat")
                && !it.name.endsWith(".jar")
    }
    // create a directory called out-notes
    println("Creating output directory...")
    File("out-notes").mkdir()

    // create a note with the same name as image .md
    picturesInFolder.forEach {
        val currentFileName = it.name.replace(" ", "-")
        println("Creating note for $currentFileName...")
        val currentFile = File("out-notes/${it.nameWithoutExtension}.md")
        currentFile.createNewFile()

        currentFile.writeText("![[${it.name}]]\n")
        if (originTag != null) currentFile.appendText("#$originTag")
    }

    println(
        """
            Complete ✅
            
            Please copy the contents of this folder - ${File(".").absolutePath} - to your Obsidian vault in the folder "pictures" in the root directory
            And the contents of ${File("out-notes").absolutePath} to any obsidian folder of your choice
            
        """.trimIndent()
    )
}