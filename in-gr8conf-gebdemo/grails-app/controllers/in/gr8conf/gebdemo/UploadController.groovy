package in.gr8conf.gebdemo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class UploadController {


    def index() {

    }

    def upload() {
        def file = request.getFile('filename')
        file.transferTo(new File("/tmp/outputfile.txt"))

        redirect(action: 'index')
    }

}
