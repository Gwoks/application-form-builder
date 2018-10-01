import { TableDetailComponent } from '../table-detail/table-detail.component';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { KeyValueModel } from '../../../_model/key-value';
import { FormDetail } from '../../../_model/form-detail';
import { LookUpService } from '../../../_services/lookup.service';
import { ApplicationService } from '../../../_services/application.service';
import { FormsService } from '../../../_services/forms.service';
import { Globals } from '../../../_model/globals';
import { SelectedKeyValue } from '../../../_model/selected-key-value';
import { FileUploadUtils } from '../../../_utils/file-upload-utils';

@Component({
    selector: 'app-form-input',
    templateUrl: './form-input.component.html',
})
export class FormInputComponent implements OnInit {

    @ViewChild('fileInput') fileInput;
    @ViewChild(TableDetailComponent)
    private tableDetailComponent: TableDetailComponent;


    lookUps: KeyValueModel[];
    @Input() formDetail: FormDetail;
    @Input() formGroup: FormGroup;
    @Input() formReadOnly;
    @Input() isResetForms: boolean;

    // fileUploadValue untuk dikirimkan ke json
    fileUploadValue: string = null;
    isReadOnly: boolean = null;
    fileUploadSize: number;
    fileUploadName: string;
    controlName: string;

    // checkbox
    checkedBox: boolean;

    // untuk dropdown
    selectedObject: string = null;
    tempParentId: string = null;
    initTempParentId: string = null;
    tempI: number;

    // input type lookup
    lookupTypeValue = 'Klik button search';

    randomGenerate: any = '';

    constructor(
        private lookUpService: LookUpService,
        private applicationService: ApplicationService,
        private formsService: FormsService,
        private globals: Globals) {
        // this.fileUploadValue;
    }

    ngOnInit() {
        this.randomGenerate = this.formsService.getRandomString();

        this.isReadOnly = this.formDetail.readOnly || JSON.parse(this.formReadOnly);
        this.controlName = this.formDetail.fieldName;
        this.checkedBox = false;
        this.selectedObject = null;

        if (this.isResetForms) {
            this.clearFile();
        }

        this.stateInit();

        this.fileUploadOnInit();

        this.dropDownOnInit();

        if (this.formDetail.lkpValue != null
            && this.formDetail.controlType !== 'dropdown'
            && this.formDetail.controlType !== 'lookup') {
            this.lookUpService.getLookUp(this.formDetail.lkpValue).subscribe(lookUps => this.lookUps = lookUps);
        }
    }

    ngAfterContentChecked(): void {
        // kalo parent nya berubah, maka si child di set null
        // kalo dia child
        if (this.formDetail.controlType === 'dropdown') {
            if (this.tempParentId !== this.initTempParentId) {
                // digunain buat oninit
                if (this.tempI > 0) {
                    this.selectedObject = this.formDetail.value;
                    // this.initTempParentId = this.formDetail.value;
                    // this.tempParentId = this.formDetail.value;
                    this.initTempParentId = this.tempParentId;
                    this.lookUps = [];

                    let lkpValue = this.formDetail.lkpValue + this.randomGenerate;
                    let selectedKeyValue: SelectedKeyValue = new SelectedKeyValue(lkpValue, this.selectedObject);
                    this.lookUpService.addSelectedKeyValue(selectedKeyValue);
                    this.setGlobalVariable();
                    this.tempI--;
                } else {
                    this.selectedObject = null;
                    this.initTempParentId = this.tempParentId;
                    this.lookUps = [];

                    let lkpValue = this.formDetail.lkpValue + this.randomGenerate;
                    let selectedKeyValue: SelectedKeyValue = new SelectedKeyValue(lkpValue, this.selectedObject);
                    this.lookUpService.addSelectedKeyValue(selectedKeyValue);
                    this.setGlobalVariable();
                }

            }
        }

    }

    ngAfterViewInit() {
        if (this.formDetail.controlType === 'dropdown') {
            if (this.formDetail.lkpParent == null) {
                this.lookUpService.getLookUp(this.formDetail.lkpValue).subscribe(lookUps => this.lookUps = lookUps);
            } else if (this.formDetail.lkpParent != null) {
                this.lookUpService.selectedKeyValueSubject.subscribe(value => {
                    let lkpParent = this.formDetail.lkpParent + this.randomGenerate;
                    this.tempParentId = getParentValue(lkpParent, value);
                    this.updateLookUpsDropdown();
                });
            }
        }
    }

    onFileChange($event): void {
        if ($event.target.files && $event.target.files[0]) {
            let reader = new FileReader();
            let file = $event.target.files[0];

            reader.onload = ($event: any) => {
                // set nama file
                this.fileUploadName = FileUploadUtils.getFileUploadName(file.name);

                this.fileUploadValue = fileUploadCreateStringValue($event.target.result, file.name);

                this.formGroup.controls[this.controlName].patchValue(this.fileUploadValue);

                this.fileUploadSize = FileUploadUtils.getFileUploadSize(
                    this.fileUploadValue.substring(this.fileUploadValue.lastIndexOf(':') + 1, this.fileUploadValue.length));
                // this.form.controls[controlName].setValue(this.fileUploadValue);
            };
            reader.readAsDataURL($event.target.files[0]);
        }
    }

    setGlobalVariable(): void {
        // let tempOptions = {};
        // let tempKeyValue = [];
        let tempKeyValueModel: KeyValueModel = null;

        //set model ke dalam bentuk {'key':'value'}
        let key = this.formDetail.fieldName + this.randomGenerate;
        let value = this.formGroup.controls[this.formDetail.fieldName].value;
        // setKeyValueModel(tempOptions, tempKeyValue, key, value);

        if (this.formDetail.calculation) {
            //set model ke dalam bentuk {'key': 'key', 'value': 'value'}
            tempKeyValueModel = new KeyValueModel(key, value);
            this.globals.pushLookupFormInputKeyValue(tempKeyValueModel);
        }
    }

    setSelected() {
        let selectedKeyValue: SelectedKeyValue = new SelectedKeyValue(this.formDetail.lkpValue + this.randomGenerate, this.selectedObject);
        this.lookUpService.addSelectedKeyValue(selectedKeyValue);
    }

    setChecked(value: boolean) {
        this.formGroup.controls[this.controlName].patchValue(value);
    }

    isDisable(): boolean {
        if (this.formDetail.lkpParent != null) {
            return true;
        }
        return false;
    }

    // resetRadio(): void {
    // this.formGroup.controls[this.controlName].reset();
    // }

    lookupTypeOnClick(event: KeyValueModel[]) {
        this.lookupTypeValue = '';
        event.forEach(element => {
            this.lookupTypeValue = this.lookupTypeValue + element.key + ': ' + element.value + '; ';
        });
        this.formGroup.controls[this.controlName].patchValue(event[0].value);
    }

    onLookupClick() {
        let lookupValue = this.formDetail.lkpValue;
        this.tableDetailComponent.getTableDetailContent(lookupValue, this.formDetail.parameter, this.randomGenerate);
    }

    clearFile() {
        // supaya path/nama file di browser jadi kosong
        // this.fileInput.nativeElement.value = null;
        this.formDetail.value = null;
        this.formGroup.controls[this.controlName].reset();
    }

    stateInit() {
        if (this.applicationService.isStateNew) {
            this.tempI = 0;
        } else {
            this.tempI = 1;
        }
    }

    dropDownOnInit() {
        if (this.formDetail.controlType === 'dropdown') {
            // kalo true, maka gausah di set init valuenya
            if (!this.isResetForms) {
                this.selectedObject = this.formDetail.value;
            }
            // this.initTempParentId = this.formDetail.value;
            // this.tempParentId = this.formDetail.value;
            if (this.formDetail.value != null) {
                let lkpValue = this.formDetail.lkpValue + this.randomGenerate;
                let selectedKeyValue: SelectedKeyValue = new SelectedKeyValue(lkpValue, this.selectedObject);
                this.lookUpService.addSelectedKeyValue(selectedKeyValue);
                this.setGlobalVariable();
            }
        }
    }

    fileUploadOnInit() {
        // kalo dia type file lgs keisi valuenya
        if (this.formDetail.controlType === 'fileupload' && this.formDetail.value != null) {
            let valueFileUpload: string[] = this.formDetail.value.split(':', 3);

            this.fileUploadName = valueFileUpload[0] + '.' + valueFileUpload[1];
            this.fileUploadSize = FileUploadUtils.getFileUploadSize(valueFileUpload[2]);
        }
    }

    updateLookUpsDropdown() {
        if (this.formDetail.controlType === 'dropdown') {

            // this.formDetail.lkpParent != null berati dia child
            if (this.formDetail.lkpParent != null) {
                if (this.tempParentId != null && (this.tempParentId === this.initTempParentId) === false) {
                    // get lookups untuk childnya tersebut
                    this.lookUpService.getLookUpByParentId(this.formDetail.lkpValue, this.tempParentId)
                        .subscribe(lookUps => {
                            this.lookUps = lookUps;
                        });
                }
            }
        }
    }

    get isMaxLength() {
        return this.formGroup.controls[this.formDetail.fieldName].hasError('maxlength');
    }

    get isEmail() {
        return this.formGroup.controls[this.formDetail.fieldName].hasError('email');
    }

    get isRequired() {
        return this.formGroup.controls[this.formDetail.fieldName].hasError('required');
    }

    get isFileSize() {
        return this.formGroup.controls[this.formDetail.fieldName].hasError('filesize');
    }

    get isFileType() {
        return this.formGroup.controls[this.formDetail.fieldName].hasError('filetype');
    }

    get isMandatory() {
        return this.formDetail.mandatory;
    }
}

function setKeyValueModel(options: {}, keyValueModel: any[], key: any, value: any): void {
    options[key] = value;
    keyValueModel.push(options);
}

function fileUploadCreateStringValue($event: any, cFileName: string): string {
    // replace data:image or ;base64 dengan string kosong
    let stringValue: string = $event.replace(/^data:(text|image|audio|video|application)\//, '');
    stringValue = stringValue.replace(/(text|image|audio|video|application)\//, '');
    stringValue = stringValue.replace(/;base64,/, ':');
    // https://stackoverflow.com/questions/38633061/how-can-i-strip-the-dataimage-part-from-a-base64-string-of-any-image-type-in-ja;

    let name: string[] = cFileName.split('.');
    // kalo tipe filenya gk kebaca
    if (stringValue.slice(0, stringValue.indexOf(':')).includes('data')) {
        // untuk menghapus tulisna ;base64
        let newchar: string = stringValue.substring(5);

        // split stringnya by . (titik), kemudian ambil indeks terakhir (ekstensi filenya)
        // get fakepath/asdasd.jkl
        let fileContent: string = this.fileInput.nativeElement.value;

        let custExtention: string = fileContent.substring(fileContent.lastIndexOf('.') + 1, fileContent.length);

        return name[0] + ':' + custExtention + newchar;
    }

    return name[0] + ':' + stringValue;
}

function getParentValue(lkpValue: string, selectedKeyValues: SelectedKeyValue[]): string {
    let result: string = null;
    for (let i = 0; i < selectedKeyValues.length; i++) {
        if (selectedKeyValues[i].lkpValue === lkpValue) {
            // this.selectedKeyValue = Observable.of(this.selectedKeyValues[i].selected);
            return selectedKeyValues[i].selected;
        }
    }
    return result;
}
