import { PostType } from './postType';
import { WikiData } from './wikiData';

export class Community
 {
    id: number;
    name: string;
    description: string;
    semanticTag: string;
    selectedTags: string;
    bannerUrl: string;
    postTypes: Array<PostType>;
}