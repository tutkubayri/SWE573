import { Enum } from './enum';

export class FormArea
 {
    id: number;
    label: string;
    dataType: string;
    postTypeId: number;
    requirement: Boolean;
    enum: Enum;
}