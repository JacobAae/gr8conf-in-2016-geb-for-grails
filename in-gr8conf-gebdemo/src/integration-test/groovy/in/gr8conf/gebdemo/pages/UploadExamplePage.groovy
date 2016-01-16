package in.gr8conf.gebdemo.pages

import geb.Page

class UploadExamplePage extends Page {

    static url = '/upload'

    static content = {
        fileUpload { $(name: 'filename') }
        submitButton{ $('button') }
    }

    static at = {
        title == 'Upload Example'
    }

    void uploadFile(String filename) {
        String filenameWithPath = "/tmp/${filename}"
        println "Filename: ${filenameWithPath}"
        File file = new File(filenameWithPath)
        assert file.exists()
        $("form").filename = filenameWithPath
        submitButton.click()
    }

}

