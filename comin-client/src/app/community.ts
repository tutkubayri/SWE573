import { PostType } from './postType';

export class Community
 {
    id: number;
    name: string;
    description: string;
    semanticTag: string;
    bannerUrl: string;
    postTypes: Array<PostType>;
}