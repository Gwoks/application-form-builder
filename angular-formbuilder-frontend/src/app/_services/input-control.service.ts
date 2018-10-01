import { Injectable } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { FormDetail } from '../_model/form-detail';
import { FileUploadUtils } from '../_utils/file-upload-utils';

@Injectable()
export class InputControlService {
    constructor() {
    }

    toFormGroup(formDetails: FormDetail[]) {
        let group: any = {};
        formDetails.forEach(formDetail => {
            switch (formDetail.inputType) {
                case 'file':
                    group[formDetail.fieldName] = fileValidator(formDetail)
                    break;

                default:
                    group[formDetail.fieldName] = basicValidator(formDetail)
                    break;
            }
        });
        return new FormGroup(group);
    }
}

function fileValidator(formDetail: any) {
    return new FormControl(formDetail.value || '',
        Validators.compose([
            requiredValidator(formDetail.mandatory),
            fileTypeValidator(formDetail),
            fileSizeValidator(formDetail),
        ]));
}

function basicValidator(formDetail: any) {
    return new FormControl(formDetail.value || '',
        Validators.compose([
            requiredValidator(formDetail.mandatory),
            lengthValidator(formDetail.length),
            mailValidator(formDetail)
        ]));
}

function requiredValidator(mandatory: boolean) {
    if (mandatory === true) {
        return Validators.required;
    }
    return null;
}

function lengthValidator(length: number) {
    if (length === 0) {
        return null;
    }
    return Validators.maxLength(length);
}

function mailValidator(formDetail: FormDetail): ValidatorFn {
    // if (formDetail.inputType == 'email') {
    //   return (control: AbstractControl): { [key: string]: boolean } | null => {
    //     let email = control.value;
    //     if (control.value !== null && !control.value.isUndefined == true || (isNaN(email) || email.indexOf('@') != -1)) {
    //       let [_, domain] = control.value.split('@');
    //       if (domain !== 'gmail.com') {
    //         return {'email': true};
    //       }
    //     }
    //     return null
    //   }
    // }

    if (formDetail.inputType == 'email') {
        return Validators.email;
    }
    return null;

}

function fileTypeValidator(formDetail: FormDetail): ValidatorFn {
    if (formDetail.inputType === 'file') {
        return (control: AbstractControl): { [key: string]: boolean } | null => {
            if (control.value !== null && control.value != '') {

                let fileType: string = FileUploadUtils.getFileUploadType(control.value);

                // disini, allowed type dari formdetail di replace , (koma) nya kemudian di split by . (titik)
                let allowedtypefile = formDetail.allowedType.replace(/,/g, '');
                let request: string[] = allowedtypefile.split('.');

                // di cek apakah value tadi masuk ke bagian terlarang atau gk?
                let asd: boolean = request.indexOf(fileType) > 0;
                if (asd) {
                    return null;
                }
            }
            return { 'filetype': true };
        }
    }
}

function fileSizeValidator(formDetail: FormDetail): ValidatorFn {
    if (formDetail.inputType === 'file') {
        return (control: AbstractControl): { [key: string]: boolean } | null => {

            if (control.value !== '' && control.value != null) {
                // value dari awal, di split dan diambil yg base64 aja (value misal png:asdasd(base64)
                let result = control.value.split(':', 3);

                let size = FileUploadUtils.getFileUploadSize(result[2]);

                if (!(size > formDetail.allowedSize)) { return null; }
            }
            return { 'filesize': true };
        };
    }
}
