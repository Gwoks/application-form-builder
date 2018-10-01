import { FormWrapper } from '../_wrapper/form.wrapper';

export class Application {
    name: string;
    id: string;
    type: string;
    id_row: string;
    value?: FormWrapper;
    values?: FormWrapper[];
    header_value?: string;
    execution?: string;
    is_readonly?: boolean;
    validate_first?: boolean;
    state?: string;
    childs: Application[];
}
