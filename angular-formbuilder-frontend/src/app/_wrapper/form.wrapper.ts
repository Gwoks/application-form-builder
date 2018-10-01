import { FormHeader } from '../_model/form-header';
import { FormVersion } from '../_model/form-version';
import { FormDetail } from '../_model/form-detail';

export class FormWrapper {
    formHeader: FormHeader;
    formVersion: FormVersion;
    listFormDetail: FormDetail[];
}
